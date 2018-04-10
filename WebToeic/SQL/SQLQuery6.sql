
CREATE TABLE member(
memberId int primary key not null IDENTITY(1, 1),
memberName nvarchar(45) not null,
memberPass nvarchar(45) not null,
categoryMemberId int,
name nvarchar(45) not null
)
select * from member

create table categoryMember(
categoryMemberId int primary key IDENTITY(1, 1),
categoryMemberName nvarchar(45)
)
select * from categoryMember

alter table dbo.member
add constraint FK_member
foreign key(categoryMemberId)
references dbo.categoryMember(categoryMemberId)

create table grammarGuideline(
 grammarGuidelineId int primary key  IDENTITY(1, 1),
 grammarName nvarchar(45),
 grammarImage nvarchar(45),
 content nvarchar(200)
)
select * from grammarGuideline

create table cmtGrammar(
cmtGrammarId int primary key IDENTITY(1, 1),
cmtGrammarContent nvarchar(200),
memberId int,
grammarGuidelineId int
)
select * from cmtGrammar

alter table cmtGrammar
add constraint FK_cmtGrammar_member
foreign key(memberId)
references member(memberId)

alter table cmtGrammar
add constraint FK_cmtGrammar_grammarGuideline
foreign key(grammarGuidelineId)
references grammarGuideline(grammarGuidelineId)

create table cmtVocabulary(
cmtVocabularyId int primary key  IDENTITY(1, 1),
cmtVocabularyContent nvarchar(200),
memberId int,
vocabularyGuideline int
)

alter table cmtVocabulary
add constraint FK_cmtVocabulary_member
foreign key(memberId)
references member(memberId)
 
create table vocabularyGuideline(
vocabularyGuidelineId int primary key IDENTITY(1, 1),
vocabularyName nvarchar(45),
vocabularyImage nvarchar(45)
)

create table vocabularyContent(
vocabularyContentId int primary key IDENTITY(1, 1),
vocabularyContentName nvarchar(45),
transcribe nvarchar(200),
audioMp3 nvarchar(45),
audioGg nvarchar(45),
mean nvarchar(200),
vocabularyGuidelineId int
)

alter table vocabularyContent
add constraint FK_vocabularyContent_vocabularyGuideline
foreign key(vocabularyGuidelineId)
references vocabularyGuideline(vocabularyGuidelineId)

create table result(
resultId int primary key IDENTITY(1, 1),
correctAnswerNum int,
incorrectAnswerNum int,
times datetime,
examinationId int,
memberId int
)

alter table result
add constraint FK_result_member
foreign key(memberId)
references member(memberId)

create table examination(
examinationId int primary key IDENTITY(1, 1),
examinationName nvarchar(45),
examinationImage nvarchar(45)
)


add constraint FK_result_examination
foreign key(examinationId)
references examination(examinationId)

create table examinationQuestion(
examinationQuestionId int primary key IDENTITY(1, 1),
num int,
imageQuestion nvarchar(45),
audioGg nvarchar(45),
audioMp3 nvarchar(45),
paragraph nvarchar(200),
question nvarchar(200),
option1 text,
option2 text,
option3 text,
option4 text,
correctAnswer nvarchar(200),
examinationId int
)

alter table examinationQuestion
add constraint FK_examinationQuestion_examination
foreign key(examinationId)
references examination(examinationId)

alter table cmtVocabulary
add constraint FK_cmtVocabulary_vocabularyGuideline
foreign key (vocabularyGuidelineId)
references vocabularyGuideline(vocabularyGuidelineId)

create table listenQuestion(
listenQuestionId int primary key IDENTITY(1, 1),
imageName nvarchar(45),
audioMp3 nvarchar(45),
audioGg nvarchar(45),
question nvarchar(200),
option1 text,
option2 text,
option3 text,
option4 text,
correctAnswer nvarchar(45),
listenExerciseId int
)

create table listenExercise(
listenExerciseId int primary key IDENTITY(1, 1),
listenExerciseName nvarchar(45),
listenExerciseImage nvarchar(45)
)


alter table listenQuestion
add constraint FK_listenQuestion_listenExercise
foreign key (listenExerciseId)
references listenExercise(listenExerciseId)

create table slideBanner(
slideBannerId int primary key IDENTITY(1, 1),
slideName text,
slideContent text,
slideImage nvarchar(45)
)


create table readExercise(
readExerciseId int primary key IDENTITY(1, 1),
readName nvarchar(45),
readImage nvarchar(45)
)

create table readQuestion(
readQuestionId int primary key IDENTITY(1, 1),
num int,
paragraph nvarchar(200),
question nvarchar(200),
option1 text,
option2 text,
option3 text,
option4 text,
correctAnswer nvarchar(45),
readExerciseId int
)

alter table readQuestion
add constraint FK_readQuestion_readExercise
foreign key (readExerciseId)
references readExercise(readExerciseId)



